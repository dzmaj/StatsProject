import { Player } from './../models/player';
import { User } from './../models/user';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'mostRecentName'
})
export class MostRecentNamePipe implements PipeTransform {

  transform(user: User, ...args: unknown[]): Player {
    let result: string;

    user.players.sort(
      (a: Player, b: Player) => {
        return a.team.game.id - b.team.game.id
      }
    )
    return user.players[0];
  }

}
