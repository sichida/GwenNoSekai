package fr.ichida.cms.web.rest;

import fr.ichida.cms.Application;
import fr.ichida.cms.domain.Blog;
import fr.ichida.cms.repository.BlogRepository;
import fr.ichida.cms.service.BlogService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the BlogResource REST controller.
 *
 * @see BlogResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class BlogResourceIntTest {

    private static final String DEFAULT_BLOG_ID = "AAAAA";
    private static final String UPDATED_BLOG_ID = "BBBBB";
    private static final String DEFAULT_BLOG_TITLE = "AAAAA";
    private static final String UPDATED_BLOG_TITLE = "BBBBB";
    private static final String DEFAULT_BLOG_SEO_DESCRIPTION = "AAAAA";
    private static final String UPDATED_BLOG_SEO_DESCRIPTION = "BBBBB";

    @Inject
    private BlogRepository blogRepository;

    @Inject
    private BlogService blogService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restBlogMockMvc;

    private Blog blog;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BlogResource blogResource = new BlogResource();
        ReflectionTestUtils.setField(blogResource, "blogService", blogService);
        this.restBlogMockMvc = MockMvcBuilders.standaloneSetup(blogResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        blogRepository.deleteAll();
        blog = new Blog();
        blog.setBlogId(DEFAULT_BLOG_ID);
        blog.setBlogTitle(DEFAULT_BLOG_TITLE);
        blog.setBlogSeoDescription(DEFAULT_BLOG_SEO_DESCRIPTION);
    }

    @Test
    public void createBlog() throws Exception {
        int databaseSizeBeforeCreate = blogRepository.findAll().size();

        // Create the Blog

        restBlogMockMvc.perform(post("/api/blogs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(blog)))
                .andExpect(status().isCreated());

        // Validate the Blog in the database
        List<Blog> blogs = blogRepository.findAll();
        assertThat(blogs).hasSize(databaseSizeBeforeCreate + 1);
        Blog testBlog = blogs.get(blogs.size() - 1);
        assertThat(testBlog.getBlogId()).isEqualTo(DEFAULT_BLOG_ID);
        assertThat(testBlog.getBlogTitle()).isEqualTo(DEFAULT_BLOG_TITLE);
        assertThat(testBlog.getBlogSeoDescription()).isEqualTo(DEFAULT_BLOG_SEO_DESCRIPTION);
    }

    @Test
    public void checkBlogIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = blogRepository.findAll().size();
        // set the field null
        blog.setBlogId(null);

        // Create the Blog, which fails.

        restBlogMockMvc.perform(post("/api/blogs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(blog)))
                .andExpect(status().isBadRequest());

        List<Blog> blogs = blogRepository.findAll();
        assertThat(blogs).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkBlogTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = blogRepository.findAll().size();
        // set the field null
        blog.setBlogTitle(null);

        // Create the Blog, which fails.

        restBlogMockMvc.perform(post("/api/blogs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(blog)))
                .andExpect(status().isBadRequest());

        List<Blog> blogs = blogRepository.findAll();
        assertThat(blogs).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllBlogs() throws Exception {
        // Initialize the database
        blogRepository.save(blog);

        // Get all the blogs
        restBlogMockMvc.perform(get("/api/blogs?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(blog.getId())))
                .andExpect(jsonPath("$.[*].blogId").value(hasItem(DEFAULT_BLOG_ID.toString())))
                .andExpect(jsonPath("$.[*].blogTitle").value(hasItem(DEFAULT_BLOG_TITLE.toString())))
                .andExpect(jsonPath("$.[*].blogSeoDescription").value(hasItem(DEFAULT_BLOG_SEO_DESCRIPTION.toString())));
    }

    @Test
    public void getBlog() throws Exception {
        // Initialize the database
        blogRepository.save(blog);

        // Get the blog
        restBlogMockMvc.perform(get("/api/blogs/{id}", blog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(blog.getId()))
            .andExpect(jsonPath("$.blogId").value(DEFAULT_BLOG_ID.toString()))
            .andExpect(jsonPath("$.blogTitle").value(DEFAULT_BLOG_TITLE.toString()))
            .andExpect(jsonPath("$.blogSeoDescription").value(DEFAULT_BLOG_SEO_DESCRIPTION.toString()));
    }

    @Test
    public void getNonExistingBlog() throws Exception {
        // Get the blog
        restBlogMockMvc.perform(get("/api/blogs/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateBlog() throws Exception {
        // Initialize the database
        blogRepository.save(blog);

		int databaseSizeBeforeUpdate = blogRepository.findAll().size();

        // Update the blog
        blog.setBlogId(UPDATED_BLOG_ID);
        blog.setBlogTitle(UPDATED_BLOG_TITLE);
        blog.setBlogSeoDescription(UPDATED_BLOG_SEO_DESCRIPTION);

        restBlogMockMvc.perform(put("/api/blogs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(blog)))
                .andExpect(status().isOk());

        // Validate the Blog in the database
        List<Blog> blogs = blogRepository.findAll();
        assertThat(blogs).hasSize(databaseSizeBeforeUpdate);
        Blog testBlog = blogs.get(blogs.size() - 1);
        assertThat(testBlog.getBlogId()).isEqualTo(UPDATED_BLOG_ID);
        assertThat(testBlog.getBlogTitle()).isEqualTo(UPDATED_BLOG_TITLE);
        assertThat(testBlog.getBlogSeoDescription()).isEqualTo(UPDATED_BLOG_SEO_DESCRIPTION);
    }

    @Test
    public void deleteBlog() throws Exception {
        // Initialize the database
        blogRepository.save(blog);

		int databaseSizeBeforeDelete = blogRepository.findAll().size();

        // Get the blog
        restBlogMockMvc.perform(delete("/api/blogs/{id}", blog.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Blog> blogs = blogRepository.findAll();
        assertThat(blogs).hasSize(databaseSizeBeforeDelete - 1);
    }
}
