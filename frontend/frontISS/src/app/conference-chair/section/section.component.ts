import { Component, OnInit } from '@angular/core';
import {Section} from '../../models/section';

@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent implements OnInit {
  section: Section;
  constructor() { }

  ngOnInit() {
  }

  onSubmit() {

  }
}