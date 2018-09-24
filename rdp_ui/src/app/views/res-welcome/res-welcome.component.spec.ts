import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ResWelcomeComponent } from './res-welcome.component';

describe('ResWelcomeComponent', () => {
    let component: ResWelcomeComponent;
    let fixture: ComponentFixture<ResWelcomeComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ResWelcomeComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ResWelcomeComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});