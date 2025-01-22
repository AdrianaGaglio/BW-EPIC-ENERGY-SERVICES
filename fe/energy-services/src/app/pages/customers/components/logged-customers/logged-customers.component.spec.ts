import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoggedCustomersComponent } from './logged-customers.component';

describe('LoggedCustomersComponent', () => {
  let component: LoggedCustomersComponent;
  let fixture: ComponentFixture<LoggedCustomersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoggedCustomersComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoggedCustomersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
