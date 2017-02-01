export class Article {
  id?: string;
  title: string;
  content: string;
  creationDate?: Date;
}

export class Post extends Article {
  thumb: string;
  author: string;
}
