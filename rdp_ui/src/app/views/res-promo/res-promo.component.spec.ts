import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ResPromoComponent } from './res-promo.component';

describe('ResPromoComponent', () => {
    let component: ResPromoComponent;
    let fixture: ComponentFixture<ResPromoComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ResPromoComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ResPromoComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});