import { Component, OnInit, ViewEncapsulation, ViewChild } from '@angular/core';
import { ScheduleProvider } from '../../providers/schedule';
import { ActivatedRoute, Params } from '@angular/router';
import { HTTP } from '../../utilities/const';
import { FileUploader } from 'ng2-file-upload';
import { FileProvider } from '../../providers/file';
import { Utils } from 'app/utilities/utils';
import { ModalDirective } from 'ngx-bootstrap';

const URL = 'http://localhost:8080/api/upload';

@Component({
    selector: 'app-schedule-details',
    templateUrl: './schedule-details.component.html',
    styleUrls: ['./schedule-details.component.scss'],
    encapsulation: ViewEncapsulation.None
})


export class ScheduleDetailsComponent implements OnInit {
    public sfid: string = "";
    public checkIv =false;
    public checkCd =false;
    public checkAtt = false;
    public entity: any = {};
    public file: any;
    filesToUpload: Array<File> = [];
    public uploader: FileUploader = new FileUploader({ url: URL, itemAlias: 'photo' });
    public cnList = [];
    public ivList = [];
    public caList = [];
    public settings = {
        selectMode: 'single',  //single|multi
        hideHeader: false,
        hideSubHeader: false,
        actions: {
            add: false,
            edit: false,
            delete: false,
            custom: [],
        },
        handle: {
            editable: false
        },
        noDataMessage: 'No data found',
        columns: {
            no: {
                title: 'No',
                filter: false,
                type: 'html',
                valuePrepareFunction: (cell, row) => {
                    return `<a href="/#/pages/credit-notes-details/${row.sfid}">${row.no}</a>`
                },
            },
            customer: {
                title: 'Customer / Branch',
                type: 'string',
                filter: false
            },
            creditNo: {
                title: 'CN No.',
                type: 'string',
                filter: false
            },
            creditDate: {
                title: 'CN Date',
                type: 'date',
                valuePrepareFunction: (value) => { return this.utl.formatDate(value, 'dd-MMM-yyyy') },
                filter: false
            },
            amountInvNo: {
                title: 'CN Amount / Applied INV No.',
                type: 'string',
                filter: false
            },
            status: {
                title: 'Status',
                type: 'string',
                filter: false
            },
            applyCN: {
                title: 'Apply CN',
                type: 'html',
                filter: false,
                valuePrepareFunction: (cell, row) => {

                    return 'aaa'
                },
            },
            unappliedReason: {
                title: 'Unapplied Reason',
                type: 'string',
                filter: false
            }
        }
    };

    public settingsIv = {
        selectMode: 'single',  //single|multi
        hideHeader: false,
        hideSubHeader: false,
        actions: {
            add: false,
            edit: false,
            delete: false,
            custom: [],
        },
        handle: {
            editable: false
        },
        noDataMessage: 'No data found',
        columns: {
            no: {
                title: 'No',
                filter: false,
                type: 'html',
                valuePrepareFunction: (cell, row) => {
                    return `<a href="/#/pages/invoices-details/${row.sfid}">${row.no}</a>`
                },
            },
            customerexcel: {
                title: 'Customer From Excel',
                type: 'string',
                filter: false,
                class: (this.checkIv)? 'd-none':""
            },
            customerBranch: {
                title: 'Customer / Branch',
                type: 'string',
                filter: false,
                class: (this.checkIv)? 'd-none':""
            },
            // supplierExcel: {
            //     title: 'Customer From Excel',
            //     type: 'string',
            //     filter: false,
            //     class: (this.checkIv)? '':"d-none"
            // },
            // supplier: {
            //     title: 'Customer / Branch',
            //     type: 'string',
            //     filter: false,
            //     class: (this.checkIv)? '':"d-none"
            // },
            invoiceNo: {
                title: 'Invoice No.',
                type: 'string',
                filter: false
            },
            creditDate: {
                title: 'Invoice Date / Credit Peiod (Days)',
                type: 'string',
                filter: false
            },
            invoiceAmount: {
                title: 'Invoice Amount',
                type: 'string',
                filter: false
            },
            Po: {
                title: 'PO / Contract',
                type: 'string',
                filter: false
            },
            status: {
                title: 'Status',
                type: 'string',
                filter: false
            },            
            rejectReason: {
                title: 'Reject Reason',
                type: 'string',
                filter: false
            }
        }
    };

    public settingsCas = {
        selectMode: 'single',  //single|multi
        hideHeader: false,
        hideSubHeader: false,
        actions: {
            add: false,
            edit: false,
            delete: false,
            custom: [],
        },
        handle: {
            editable: false
        },
        noDataMessage: 'No data found',
        columns: {
            no: {
                title: 'No',
                filter: false,
                type: 'html',
                valuePrepareFunction: (cell, row) => {
                    return `<a href="/#/pages/invoices-details/${row.sfid}">${row.no}</a>`
                },
            },           
            supplierExcel: {
                title: 'Customer From Excel',
                type: 'string',
                filter: false,
                class: (this.checkIv)? '':"d-none"
            },
            supplier: {
                title: 'Customer / Branch',
                type: 'string',
                filter: false,
                class: (this.checkIv)? '':"d-none"
            },
            invoiceNo: {
                title: 'Invoice No.',
                type: 'string',
                filter: false
            },
            creditDate: {
                title: 'Invoice Date / Credit Peiod (Days)',
                type: 'string',
                filter: false
            },
            invoiceAmount: {
                title: 'Invoice Amount',
                type: 'string',
                filter: false
            },
            Po: {
                title: 'PO / Contract',
                type: 'string',
                filter: false
            },
            status: {
                title: 'Status',
                type: 'string',
                filter: false
            },            
            rejectReason: {
                title: 'Reject Reason',
                type: 'string',
                filter: false
            }
        }
    };

    @ViewChild('authorsiedModal') public authorsiedModal: ModalDirective;
    
    constructor(
        private act: ActivatedRoute,
        private pro: ScheduleProvider,
        private filepro: FileProvider,
        private utl: Utils) { }

    ngOnInit() {
        this.act.params.subscribe((params: Params) => {
            this.sfid = params["_id"];
            this.getDetail(this.sfid);
        });
    }
    public getDetail(sfid) {
        this.pro.getById(sfid).subscribe((rsp: any) => {
            console.log(rsp);
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.entity = rsp.result;
                if(this.entity.documentType=="Credit Note")
                {
                    this.checkCd = true;
                }
                if(this.entity.documentType=="Invoice" || this.entity.documentType=="Cash Disbursement")
                {
                    this.checkIv = true;
                }

                let i = 0;
                let tmpCNList = this.entity.lstCreditNote;
                tmpCNList.forEach(element => {
                    i++;
                    element.no = i;
                    element.amountInvNo = element.creditAmount.toLocaleString() + ' / ' + element.appliedInvoiceNo;
                    element.customerBranch = element.customer + ' / ' + element.customerBranch;
                });
                this.cnList = tmpCNList;
               
                let tmpIVList = this.entity.lstInvoice;
                tmpIVList.forEach(element => {
                    i++;
                    element.no = i;                   
                    element.customerexcel = element.customerFromExcel;
                    element.invoiceAmount = element.invoiceAmount.toLocaleString();
                    element.customerBranch = element.customer + ' / ' + element.customerBranch;
                    element.creditDate = this.utl.formatDate(element.invoiceDate, 'dd-MMM-yyyy') + ' / ' + element.creditPeriod;
                    element.Po = element.po + ' / ' + element.contract;
                });
                this.ivList = tmpIVList;

                let tmpCaList = this.entity.lstInvoice;
                tmpCaList.forEach(element => {
                    i++;
                    element.no = i;
                    element.supplierExcel = element.supplierExcel;
                    element.invoiceAmount = element.invoiceAmount.toLocaleString();
                    element.customerBranch = element.customer + ' / ' + element.customerBranch;
                    element.creditDate = this.utl.formatDate(element.invoiceDate, 'dd-MMM-yyyy') + ' / ' + element.creditPeriod;
                    element.Po = element.po + ' / ' + element.contract;
                });
                this.caList = tmpCaList;
                this.checkAtt = true;
            }
        }, (err) => {
            console.log(err);
        });
    }
    public UpdateSchedule() {

        let x = {
            currencyIsoCode: this.entity.currencyIsoCode,
            scheduleNo: this.entity.scheduleNo,
            factorCode: this.entity.factorCode,
            //recordTypeId:this.entity.recordTypeId,
            exchangeRate: this.entity.exchangeRate,
            id: this.entity.id
        }
        this.pro.update(x).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {

            }
        }, (err) => {
            console.log(err);
        });
    }

    public Authorise() {
        let x = {
            currencyIsoCode: this.entity.currencyIsoCode,
            scheduleNo: this.entity.scheduleNo,
            factorCode: this.entity.factorCode,
            portalStatus:"Authorise",
            exchangeRate: this.entity.exchangeRate,
            id: this.entity.id
        }
        this.pro.update(x).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {

            }
        }, (err) => {
            console.log(err);
        });
    }

    public fileChangeEvent(fileInput: any) {
        console.log(fileInput);
        this.filesToUpload = <Array<File>>fileInput.target.files;
        this.file = this.filesToUpload[0];
        console.log(this.filesToUpload);
        //this.product.photo = fileInput.target.files[0]['name'];
    }

    public UpdateFile() {
        let o =
        {
            "scheduleOfOfferId": "001p000000UraJCAAZ"
        };
        let s = JSON.stringify(o);
        this.filepro.uploadmulti(this.file, s).subscribe((rsp: any) => {
            if (rsp.body != undefined) {
                let o = JSON.parse(rsp.body);
            }
        }, err => console.log(err));
    }


}
