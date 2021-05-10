import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-input-form-datepicker',
  templateUrl: './input-form-datepicker.component.html',
  styleUrls: ['./input-form-datepicker.component.css']
})

export class InputFormDatepickerComponent implements OnInit {

  @Input() label:String = '';
  @Input('maxlength') max:String = '';
  @Input('placeholder') placeholder:String = '';
  @Input('formControlName') formControlName:String = '';
  constructor() { }

  ngOnInit(): void {
  }

}
