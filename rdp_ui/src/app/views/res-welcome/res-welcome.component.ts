import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { SalesforceProvider } from '../../providers/providers';
import { saveAs } from "file-saver";

@Component({
    selector: 'app-res-welcome',
    templateUrl: './res-welcome.component.html',
    styleUrls: ['./res-welcome.component.css']
})

export class ResWelcomeComponent implements OnInit {
    constructor(private act: ActivatedRoute,
        private pro: SalesforceProvider) { }

    public factSheet: string;
    public salesAgreement: string;
    public webReferenceNo: string;
    public loader: boolean = false;

    ngOnInit() {
        document.getElementById("welcomeDiv").focus();
        this.act.params.subscribe((params: Params) => {
            this.factSheet = params["factSheet"];
            this.salesAgreement = params["salesAgreement"];
            this.webReferenceNo = params["webReferenceNo"];

            // Decode
            this.factSheet = atob(this.factSheet);
            this.salesAgreement = atob(this.salesAgreement);
            this.webReferenceNo = atob(this.webReferenceNo);
        });
    }

    ngAfterViewChecked() {
        document.getElementById("welcomeDiv").setAttribute("type","hidden");
    }

    public downloadFactSheet() {
        this.loader = true;
        this.pro.download(this.factSheet)
            .subscribe(blob => {
                let name = this.getName("FS");
                saveAs(blob, name);
                this.loader = false;
            });;
    }

    public downloadSalesAgreement() {
        this.loader = true;
        this.pro.download(this.salesAgreement)
            .subscribe(blob => {
                let name = this.getName("SA");
                saveAs(blob, name);
                this.loader = false;
            });;
    }

    private getName(name) {
        let datePipe = new DatePipe('en-us');
        let d = new Date();
        name = name + datePipe.transform(d, 'yyyyMMddHHmmss');
        return name;
    }
}