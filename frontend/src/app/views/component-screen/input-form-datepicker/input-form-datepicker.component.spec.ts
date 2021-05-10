import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InputFormDatepickerComponent } from './input-form-datepicker.component';

describe('InputFormDatepickerComponent', () => {
  let component: InputFormDatepickerComponent;
  let fixture: ComponentFixture<InputFormDatepickerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InputFormDatepickerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InputFormDatepickerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
