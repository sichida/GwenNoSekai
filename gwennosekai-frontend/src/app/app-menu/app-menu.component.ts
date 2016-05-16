import {Component, Input} from 'angular2/core';
import {MenuItem} from './menu-item';

@Component({
    selector: 'app-menu',
    template: require('./app-menu.html')
})
export class AppMenu {
    @Input() items:Array<MenuItem>;
    constructor() {}
}