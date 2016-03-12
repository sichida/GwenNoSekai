package fr.ichida.cms.web.rest;

import fr.ichida.cms.Application;
import fr.ichida.cms.domain.Theme;
import fr.ichida.cms.repository.ThemeRepository;
import fr.ichida.cms.service.ThemeService;

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
 * Test class for the ThemeResource REST controller.
 *
 * @see ThemeResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ThemeResourceIntTest {

    private static final String DEFAULT_THEME_ID = "AAAAA";
    private static final String UPDATED_THEME_ID = "BBBBB";

    @Inject
    private ThemeRepository themeRepository;

    @Inject
    private ThemeService themeService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restThemeMockMvc;

    private Theme theme;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ThemeResource themeResource = new ThemeResource();
        ReflectionTestUtils.setField(themeResource, "themeService", themeService);
        this.restThemeMockMvc = MockMvcBuilders.standaloneSetup(themeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        themeRepository.deleteAll();
        theme = new Theme();
        theme.setThemeId(DEFAULT_THEME_ID);
    }

    @Test
    public void createTheme() throws Exception {
        int databaseSizeBeforeCreate = themeRepository.findAll().size();

        // Create the Theme

        restThemeMockMvc.perform(post("/api/themes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(theme)))
                .andExpect(status().isCreated());

        // Validate the Theme in the database
        List<Theme> themes = themeRepository.findAll();
        assertThat(themes).hasSize(databaseSizeBeforeCreate + 1);
        Theme testTheme = themes.get(themes.size() - 1);
        assertThat(testTheme.getThemeId()).isEqualTo(DEFAULT_THEME_ID);
    }

    @Test
    public void checkThemeIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = themeRepository.findAll().size();
        // set the field null
        theme.setThemeId(null);

        // Create the Theme, which fails.

        restThemeMockMvc.perform(post("/api/themes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(theme)))
                .andExpect(status().isBadRequest());

        List<Theme> themes = themeRepository.findAll();
        assertThat(themes).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllThemes() throws Exception {
        // Initialize the database
        themeRepository.save(theme);

        // Get all the themes
        restThemeMockMvc.perform(get("/api/themes?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(theme.getId())))
                .andExpect(jsonPath("$.[*].themeId").value(hasItem(DEFAULT_THEME_ID.toString())));
    }

    @Test
    public void getTheme() throws Exception {
        // Initialize the database
        themeRepository.save(theme);

        // Get the theme
        restThemeMockMvc.perform(get("/api/themes/{id}", theme.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(theme.getId()))
            .andExpect(jsonPath("$.themeId").value(DEFAULT_THEME_ID.toString()));
    }

    @Test
    public void getNonExistingTheme() throws Exception {
        // Get the theme
        restThemeMockMvc.perform(get("/api/themes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateTheme() throws Exception {
        // Initialize the database
        themeRepository.save(theme);

		int databaseSizeBeforeUpdate = themeRepository.findAll().size();

        // Update the theme
        theme.setThemeId(UPDATED_THEME_ID);

        restThemeMockMvc.perform(put("/api/themes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(theme)))
                .andExpect(status().isOk());

        // Validate the Theme in the database
        List<Theme> themes = themeRepository.findAll();
        assertThat(themes).hasSize(databaseSizeBeforeUpdate);
        Theme testTheme = themes.get(themes.size() - 1);
        assertThat(testTheme.getThemeId()).isEqualTo(UPDATED_THEME_ID);
    }

    @Test
    public void deleteTheme() throws Exception {
        // Initialize the database
        themeRepository.save(theme);

		int databaseSizeBeforeDelete = themeRepository.findAll().size();

        // Get the theme
        restThemeMockMvc.perform(delete("/api/themes/{id}", theme.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Theme> themes = themeRepository.findAll();
        assertThat(themes).hasSize(databaseSizeBeforeDelete - 1);
    }
}
