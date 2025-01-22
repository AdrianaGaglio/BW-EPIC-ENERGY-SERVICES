import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForadminuserComponent } from './foradminuser.component';

describe('ForadminuserComponent', () => {
  let component: ForadminuserComponent;
  let fixture: ComponentFixture<ForadminuserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ForadminuserComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ForadminuserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
