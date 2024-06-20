import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompteMobilMoneyComponent } from './compte-mobil-money.component';

describe('CompteMobilMoneyComponent', () => {
  let component: CompteMobilMoneyComponent;
  let fixture: ComponentFixture<CompteMobilMoneyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CompteMobilMoneyComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CompteMobilMoneyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
