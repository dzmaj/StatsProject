import { User } from './../../../models/user';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-metaserver-user',
  templateUrl: './metaserver-user.component.html',
  styleUrls: ['./metaserver-user.component.css']
})
export class MetaserverUserComponent implements OnInit {

  @Input() user: User

  constructor() { }

  ngOnInit(): void {
  }

}
