import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Rx';
import {Post} from "./shared/post.entity";

@Injectable()
export class PostService {

  constructor() {
  }

  findAll(pageNumber: number, numberOfItems: number): Observable<Array<Post>> {
    const posts:Array<Post> = new Array<Post>();
    posts.push({title: 'COUSETTE ❥ ON DÉCOUVRE SON NOMBRIL AVEC NADINE', thumb: 'https://gwennosekai.com/wp-content/uploads/sites/2/2016/09/gwennosekai-diy-cousette-nadine1-800x600.jpg', content: '', creationDate: new Date()});
    posts.push({title: 'RÉCIT DE VOYAGE ❥ JAPON : RETOUR AUX SOURCES À OSAKA', thumb: 'https://gwennosekai.com/wp-content/uploads/sites/2/2016/09/gwennosekai-r%C3%A9cit-voyage-japon-osaka7-800x600.jpg', content: '', creationDate: new Date()});
    posts.push({title: 'COUSETTE ❥ IL ÉTAIT UNE PETITE MALO', thumb: 'https://gwennosekai.com/wp-content/uploads/sites/2/2016/09/gwennosekai-diy-cousette-malo2-800x600.jpg', content: '', creationDate: new Date()});
    posts.push({title: 'COUSETTE ❥ PETITS OISEAUX TOUT COLORÉS', thumb: 'https://gwennosekai.com/wp-content/uploads/sites/2/2016/07/gwennosekai-diy-cousette-mistral5-800x600.jpg', content: '', creationDate: new Date()});
    posts.push({title: 'RÉCIT DE VOYAGE ❥ CRÈTE – RÉCIT FINAL', thumb: 'https://gwennosekai.com/wp-content/uploads/sites/2/2016/07/gwennosekai-r%C3%A9cit-voyage-crete3-5-800x600.jpg', content: '', creationDate: new Date()});
    posts.push({title: 'COUSETTE ❥ TOURNE, TOURNE … MA FRIDA', thumb: 'https://gwennosekai.com/wp-content/uploads/sites/2/2016/05/gwennosekai-diy-couture-frida2-800x600.jpg', content: '', creationDate: new Date()});
    posts.push({title: 'COUSETTE ❥ PRINCESSE LEIA', thumb: 'https://gwennosekai.com/wp-content/uploads/sites/2/2016/05/gwennosekai-diy-couture-princesseleia2-800x600.jpg', content: '', creationDate: new Date()});
    posts.push({title: 'MODE ❥ J’AI TESTÉ LE SHOPPING PERSONNALISÉ AVEC MY PERSONAL CLOSET', thumb: 'https://gwennosekai.com/wp-content/uploads/sites/2/2016/04/DSC_6074-800x600.jpg', content: '', creationDate: new Date()});
    posts.push({title: 'COUSETTE ❥ POLKA-DOTTY BIANCA', thumb: 'https://gwennosekai.com/wp-content/uploads/sites/2/2016/03/gwennosekai-diy-cousette-bianca-cover-800x600.jpg', content: '', creationDate: new Date()});
    return Observable.of(posts);
  }
}
