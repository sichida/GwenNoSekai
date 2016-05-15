import {
    Component,
    Input
} from 'angular2/core';

@Component({
    selector: 'app-logo',
    template: require('./app-logo.html')
})
export class AppLogo {
    @Input() imageUrl: string;
    @Input() title: string;
    constructor() {
    }
}
