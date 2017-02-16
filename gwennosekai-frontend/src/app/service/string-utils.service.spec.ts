/* tslint:disable:no-unused-variable */
import { TestBed, inject } from '@angular/core/testing';
import { StringUtilsService } from './string-utils.service';

describe('StringUtilsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [StringUtilsService]
    });
  });

  it('should ...', inject([StringUtilsService], (service: StringUtilsService) => {
    expect(service).toBeTruthy();
  }));

  it('should re;ove accent', inject([StringUtilsService], (service: StringUtilsService) => {
    expect(service.removeAccent('éeàa')).toEqual('eeaa');
  }));
});
