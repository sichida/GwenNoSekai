import {Component, Input} from 'angular2/core';

@Component({
    selector: 'app-header',
    template: require('./app-header.html')
})
export class AppHeader {
    @Input() banner:string;
    constructor() { }
}