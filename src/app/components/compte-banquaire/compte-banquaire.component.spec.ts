import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompteBanquaireComponent } from './compte-banquaire.component';

describe('CompteBanquaireComponent', () => {
  let component: CompteBanquaireComponent;
  let fixture: ComponentFixture<CompteBanquaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CompteBanquaireComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CompteBanquaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
