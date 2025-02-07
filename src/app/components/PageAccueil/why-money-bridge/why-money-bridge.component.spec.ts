import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WhyMoneyBridgeComponent } from './why-money-bridge.component';

describe('WhyMoneyBridgeComponent', () => {
  let component: WhyMoneyBridgeComponent;
  let fixture: ComponentFixture<WhyMoneyBridgeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WhyMoneyBridgeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WhyMoneyBridgeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
