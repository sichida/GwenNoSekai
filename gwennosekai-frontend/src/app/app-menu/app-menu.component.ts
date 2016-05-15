import {Component, Input} from 'angular2/core';

@Component({
    selector: 'app-menu',
    template: require('./app-menu.html')
})
export class AppMenu {
    @Input() items:Array<Object>;
    constructor() {}
}