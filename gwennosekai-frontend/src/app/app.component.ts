/*
 * Angular 2 decorators and services
 */
import {Component, ViewEncapsulation} from 'angular2/core';
import {RouteConfig, Router} from 'angular2/router';

import {Home} from './home';
import {AppState} from './app.service';
import {RouterActive} from './router-active';
import {AppHeader} from './app-header';
import {AppLogo} from './app-logo';

/*
 * App Component
 * Top Level Component
 */
@Component({
    selector: 'app',
    pipes: [],
    providers: [],
    directives: [RouterActive, AppHeader, AppLogo],
    encapsulation: ViewEncapsulation.None,
    styles: [
        require('normalize.css'),
        require('./app.css')
    ],
    template: require('./app.html')
})
@RouteConfig([
    {path: '/', name: 'Index', component: Home, useAsDefault: true},
    {path: '/home', name: 'Home', component: Home},
    // Async load a component using Webpack's require with es6-promise-loader and webpack `require`
    {path: '/about', name: 'About', loader: () => require('es6-promise!./about')('About')}
])
export class App {
    name:string = 'Gwen No Sekai';
    logoUrl:string = 'http://i2.wp.com/gwennosekai.com/wp-content/uploads/sites/2/2015/10/gwennosekai-logo1.png?zoom=2&fit=300%2C300';
    applicationTitle:string = 'gwen no sekai';
    applicationBanner:string = 'http://www.gwennosekai.com/wp-content/uploads/sites/2/2015/08/cropped-cropped-481482__cherry-blossom_p1.jpg';

    constructor(public appState:AppState,
                public router:Router) {

    }
}
