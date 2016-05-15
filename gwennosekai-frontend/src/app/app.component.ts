/*
 * Angular 2 decorators and services
 */
import {Component, ViewEncapsulation} from 'angular2/core';
import {RouteConfig} from 'angular2/router';

import {Home} from './home';
import {AppState} from './app.service';
import {RouterActive} from './router-active';
import {AppHeader} from './app-header';
import {AppLogo} from './app-logo';
import {AppMenu} from './app-menu';

/*
 * App Component
 * Top Level Component
 */
@Component({
    selector: 'app',
    pipes: [],
    providers: [],
    directives: [RouterActive, AppHeader, AppLogo, AppMenu],
    encapsulation: ViewEncapsulation.None,
    styles: [
        require('normalize.css'),
        require('./app.css')
    ],
    template: require('./app.html')
})
@RouteConfig([
    {path: '/', name: 'Index', component: Home, useAsDefault: true}
])
export class App {
    name:string = 'Gwen No Sekai';
    logoUrl:string = 'http://i2.wp.com/gwennosekai.com/wp-content/uploads/sites/2/2015/10/gwennosekai-logo1.png?zoom=2&fit=300%2C300';
    applicationTitle:string = 'gwen no sekai';
    applicationBanner:string = 'http://www.gwennosekai.com/wp-content/uploads/sites/2/2015/08/cropped-cropped-481482__cherry-blossom_p1.jpg';
    menuItems:Array<Object> = new Array<Object>();

    constructor() {
        this.menuItems.push({route: '/', label: 'home'});
        this.menuItems.push({route: '/', label: 'Category 1'});
        this.menuItems.push({route: '/', label: 'Category 2'});
        this.menuItems.push({route: '/', label: 'Category 3'});
    }
}
