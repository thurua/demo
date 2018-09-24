import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { BizWelcomeComponent } from './biz-welcome.component';

describe('BizWelcomeComponent', () => {
    let component: BizWelcomeComponent;
    let fixture: ComponentFixture<BizWelcomeComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [BizWelcomeComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(BizWelcomeComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});