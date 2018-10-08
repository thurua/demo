import { Component, OnInit, ViewEncapsulation, ViewChild, EventEmitter } from '@angular/core';
import { ScheduleProvider } from '../../providers/schedule';
import { ActivatedRoute, Params } from '@angular/router';
import { HTTP } from '../../utilities/const';
import { FileUploader } from 'ng2-file-upload';
import { FileProvider } from '../../providers/file';
import { Utils } from 'app/utilities/utils';
import { ModalDirective } from 'ngx-bootstrap';
import { UploadOutput, UploadInput, UploadFile, humanizeBytes, UploaderOptions } from 'ngx-uploader';
import { DomSanitizer } from '@angular/platform-browser';

const URL = 'http://localhost:8080/api/upload';

@Component({
    selector: 'app-schedule-details',
    templateUrl: './schedule-details.component.html',
    styleUrls: ['./schedule-details.component.scss'],
    encapsulation: ViewEncapsulation.None
})


export class ScheduleDetailsComponent implements OnInit {
    options: UploaderOptions;
    formData: FormData;
    files: UploadFile[];
    uploadInput: EventEmitter<UploadInput>;
    humanizeBytes: Function;
    dragOver: boolean;

    public sfId: string = "";
    public checkIv = false;
    public checkCd = false;
    public checkAtt = false;
    public entity: any = {};
    public file: any;
    public pageSize = 5;
    public curentPage = 1;
    public pager: any = {};
    public mesErr = "";
    public title = "";
    public msgInfo = "";
    filesToUpload: Array<File> = [];
    public uploader: FileUploader = new FileUploader({ url: URL, itemAlias: 'photo' });
    public cnList = [];
    public ivList = [];
    public caList = [];
    public attList = [];
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
                    return `<a href="/#/pages/credit-notes-details/${row.sfId}">${row.no}</a>`
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
                valuePrepareFunction: (value) => { return this._sanitizer.bypassSecurityTrustHtml('<input type="checkbox" ' + value + ' disabled />'); },
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
                    return `<a href="/#/pages/invoices-details/${row.sfId}">${row.no}</a>`
                },
            },
            customerexcel: {
                title: 'Customer From Excel',
                type: 'string',
                filter: false
            },
            customerBranch: {
                title: 'Customer / Branch',
                type: 'string',
                filter: false
            },
            supplierExcel: {
                title: 'Supplier from Excel',
                type: 'string',
                filter: false
            },
            supplier: {
                title: 'Supplier',
                type: 'string',
                filter: false
            },
            invoiceNo: {
                title: 'Invoice No.',
                type: 'string',
                filter: false
            },
            creditDate: {
                title: 'Invoice Date / Credit Period (Days)',
                type: 'string',
                filter: false
            },
            invoiceAmount: {
                title: 'Invoice Amount',
                type: 'string',
                filter: false
            },
            po: {
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
                title: 'Rejected Reason',
                type: 'string',
                filter: false
            }
        }
    };
    public settingsAtt = {
        selectMode: 'single',  //single|multi
        hideHeader: false,
        hideSubHeader: false,
        noDataMessage: 'No data found',
        columns: {
            no: {
                title: 'No',
                filter: false,
                type: 'html',
                // valuePrepareFunction: (cell, row) => {
                //     return `<a href="/#/pages/invoices-details/${row.sfId}">${row.no}</a>`
                // },
            },
            name: {
                title: 'Attached File',
                type: 'string',
                filter: false
            },
            fileSize: {
                title: 'File Size',
                type: 'string',
                filter: false
            },
            owner: {
                title: 'Owner',
                type: 'string',
                filter: false
            },
            uploadedOn: {
                title: 'Uploaded Date/Time',
                type: 'string',
                filter: false
            }
        },
        actions: {
            add: false,
            edit: false,
            delete: true,
            custom: [],
            position: 'right'
        },
        handle: {
            editable: false
        },
        delete: {
            deleteButtonContent: '<i  class="fa fa-trash"></i>',
            confirmDelete: true,
        },
    };

    @ViewChild('authorsiedModal') public authorsiedModal: ModalDirective;
    @ViewChild('uploadModal') public uploadModal: ModalDirective;
    @ViewChild('infoModal') public infoModal: ModalDirective;

    constructor(
        private act: ActivatedRoute,
        private pro: ScheduleProvider,
        private filepro: FileProvider,
        private utl: Utils,
        private _sanitizer: DomSanitizer) {
        this.options = { concurrency: 1, maxUploads: 10 };
        this.files = []; // local uploading files array
        this.uploadInput = new EventEmitter<UploadInput>(); // input events, we use this to emit data to ngx-uploader
        this.humanizeBytes = humanizeBytes;
    }

    ngOnInit() {
        this.act.params.subscribe((params: Params) => {
            this.sfId = params["_id"];
            this.files = [];
            this.getDetail(this.sfId);
        });
    }

    onUploadOutput(output: UploadOutput): void {
        if (output.type === 'allAddedToQueue') { // when all files added in queue
            // uncomment this if you want to auto upload files when added
            // const event: UploadInput = {
            //   type: 'uploadAll',
            //   url: '/upload',
            //   method: 'POST',
            //   data: { foo: 'bar' }
            // };
            // this.uploadInput.emit(event);
        } else if (output.type === 'addedToQueue' && typeof output.file !== 'undefined') { // add file to array when added
            this.files.push(output.file);
        } else if (output.type === 'uploading' && typeof output.file !== 'undefined') {
            // update current data in files array for uploading file
            const index = this.files.findIndex(file => typeof output.file !== 'undefined' && file.id === output.file.id);
            this.files[index] = output.file;

        } else if (output.type === 'removed') {
            // remove file from array when removed
            this.files = this.files.filter((file: UploadFile) => file !== output.file);
        } else if (output.type === 'dragOver') {
            this.dragOver = true;
        } else if (output.type === 'dragOut') {
            this.dragOver = false;
        } else if (output.type === 'drop') {
            this.dragOver = false;
        }

        this.file = this.files[0];
    }

    startUpload(): void {
        let res = localStorage.getItem('CURRENT_TOKEN');
        let json = JSON.parse(res);

        let xx: File[] = [];
        this.files.forEach(i => {
            xx.push(i.nativeFile);
        });
        let o =
        {
            "scheduleOfOffer": this.sfId
        };
        let s = JSON.stringify(o);
        this.filepro.uploadmulti(xx, s).subscribe((rsp: any) => {
            if (rsp.body != undefined) {
                let o = JSON.parse(rsp.body);
                if (o.status === HTTP.STATUS_SUCCESS) {
                    this.files = [];
                    this.file = this.files[0];
                    this.entity = o.result.data;

                    let tmpattList = o.result.data;
                    let i = 0;
                    tmpattList.forEach(element => {
                        i++;
                        element.no = i;
                        element.uploadedOn = this.utl.formatDate(element.uploadedOn, 'dd-MMM-yyyy');
                    });
                    this.attList = tmpattList;
                }
                else {
                    this.mesErr = o.message;
                    this.uploadModal.show();
                }

            }
        }, err => console.log(err));


    }

    cancelUpload(id: string): void {
        this.uploadInput.emit({ type: 'cancel', id: id });
    }

    removeFile(id: string): void {
        this.uploadInput.emit({ type: 'remove', id: id });
    }

    removeAllFiles(): void {
        this.uploadInput.emit({ type: 'removeAll' });
    }

    public getDetail(sfId) {
        this.pro.getById(sfId).subscribe((rsp: any) => {
            console.log(rsp);
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.entity = rsp.result;
                if (this.entity.documentType == "Credit Note") {
                    this.checkCd = true;

                    if (this.entity.lstCreditNote != null) {
                        let tmpCNList = this.entity.lstCreditNote;
                        let i = 0;
                        tmpCNList.forEach(element => {
                            i++;
                            element.no = i;
                            element.status = "Accepted";
                            element.applyCN = element.unappliedReason != null ? "checked" : "";
                            element.amountInvNo = element.creditAmount.toLocaleString() + ' / ' + element.appliedInvoiceNo;
                            element.customerBranch = element.customer + ' / ' + element.customerBranch;
                        });
                        this.cnList = tmpCNList;
                    }
                }
                else {
                    this.checkIv = true;
                    if (this.entity.lstInvoice != null) {
                        let tmpIVList = this.entity.lstInvoice;
                        let i = 0;
                        tmpIVList.forEach(element => {
                            i++;
                            element.no = i;
                            element.customerexcel = element.customerFromExcel;
                            element.supplierExcel = element.supplierExcel;
                            element.invoiceAmount = element.invoiceAmount.toLocaleString();
                            element.customerBranch = element.customer + ' / ' + element.customerBranch;
                            element.creditDate = this.utl.formatDate(element.invoiceDate, 'dd-MMM-yyyy') + ' / ' + element.creditPeriod;
                            let po = element.po == null ? '' : element.po;
                            let contract = element.contract == null ? '' : element.contract;
                            element.po = po + ' / ' + contract;
                            if (element.po != null || element.contract != null) {
                                let re = / \/ /gi;

                                element.po = element.po.replace(re, '');
                            }
                        });
                        this.ivList = tmpIVList;
                        if (this.entity.documentType == "Invoice") {
                            delete this.settingsIv.columns.supplier;
                            delete this.settingsIv.columns.supplierExcel;
                        }
                        else {
                            delete this.settingsIv.columns.customerBranch;
                            delete this.settingsIv.columns.customerexcel;
                        }
                    }
                }
                let tmpattList = this.entity.lstAttachment
                let i = 0;
                tmpattList.forEach(element => {
                    i++;
                    element.no = i;
                    element.uploadedOn = this.utl.formatDate(element.uploadedOn, 'dd-MMM-yyyy');
                });
                this.attList = tmpattList;
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
            portalStatus: "Authorise",
            exchangeRate: this.entity.exchangeRate,
            id: this.entity.id
        }
        this.pro.update(x).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.entity.portalStatus = "Authorise";
                this.title = 'Information';
                this.msgInfo = 'Updated Successful!';
                this.infoModal.show();
            }
        }, (err) => {
            console.log(err);
        });
    }

    onDelete(event) {
        let id = event.data.id;
        let user = JSON.parse(localStorage.getItem("CURRENT_TOKEN"));
        let i = 0;
        this.attList.forEach((element, index) => {
            if (element.id == id && element.uploadedBy == user.sfId) {
                this.attList.splice(index, 1);
                this.filepro.delete(id).subscribe((rsp: any) => {
                    if (rsp.status === HTTP.STATUS_SUCCESS) {

                    }
                }, err => console.log(err));
            }
        });
        this.attList.forEach((element) => {
            i++;
            element.no = i;
        });
        event.confirm.resolve(event.source.data);
    }
}
