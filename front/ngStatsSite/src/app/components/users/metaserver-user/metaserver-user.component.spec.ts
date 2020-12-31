import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MetaserverUserComponent } from './metaserver-user.component';

describe('MetaserverUserComponent', () => {
  let component: MetaserverUserComponent;
  let fixture: ComponentFixture<MetaserverUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MetaserverUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MetaserverUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
