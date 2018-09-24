import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ResSignupComponent } from './res-signup.component';

describe('ResSignupComponent', () => {
    let component: ResSignupComponent;
    let fixture: ComponentFixture<ResSignupComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ResSignupComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ResSignupComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});