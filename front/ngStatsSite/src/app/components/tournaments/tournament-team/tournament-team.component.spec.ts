import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TournamentTeamComponent } from './tournament-team.component';

describe('TournamentTeamComponent', () => {
  let component: TournamentTeamComponent;
  let fixture: ComponentFixture<TournamentTeamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TournamentTeamComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TournamentTeamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
