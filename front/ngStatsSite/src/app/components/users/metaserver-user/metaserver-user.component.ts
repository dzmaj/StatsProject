import { Player } from './../../../models/player';
import { MostRecentNamePipe } from './../../../pipes/most-recent-name.pipe';
import { User } from './../../../models/user';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-metaserver-user',
  templateUrl: './metaserver-user.component.html',
  styleUrls: ['./metaserver-user.component.css']
})
export class MetaserverUserComponent implements OnInit {

  @Input() user: User;
  latestPlayer: Player;

  constructor(private mostRecentNamePipe: MostRecentNamePipe) { }

  ngOnInit(): void {
    // this.latestPlayer = this.mostRecentNamePipe.transform(this.user)
  }



}
