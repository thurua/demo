import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { BizSignupComponent } from './biz-signup.component';

describe('BizSignupComponent', () => {
    let component: BizSignupComponent;
    let fixture: ComponentFixture<BizSignupComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [BizSignupComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(BizSignupComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});