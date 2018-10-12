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
import { saveAs } from "file-saver";

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
    public strFileName = "";
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
                    return `<a href="/#/pages/credit-notes-details/${row.uuId}">${row.no}</a>`
                },
            },
            customerBranch: {
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
                    return `<a href="/#/pages/invoices-details/${row.uuId}">${row.no}</a>`
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
            supplierFromExcel: {
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
            custom: [{ name: 'onDownloadAction', title: '<i class="fa fa-download pad-down-icon"></i>' }],
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
        this.strFileName = "";
        if (this.files.length > 0) {
            this.files.forEach(element => {
                this.strFileName = this.strFileName + element.name + "; ";
            });
        }
    }

    startUpload(): void {
        document.getElementById('preloader').style.display = 'block';
        let xx: File[] = [];
        this.files.forEach(i => {
            xx.push(i.nativeFile);
        });
        let o =
        {
            "parentUuId": this.sfId
        };
        let s = JSON.stringify(o);
        this.filepro.uploadmulti(xx, s).subscribe((rsp: any) => {
            if (rsp.body != undefined) {
                let o = JSON.parse(rsp.body);
                if (o.status === HTTP.STATUS_SUCCESS) {
                    this.files = [];
                    this.file = this.files[0];

                    let tmpattList = o.result.data;
                    let i = 0;
                    tmpattList.forEach(element => {
                        i++;
                        element.no = i;
                        element.uploadedOn = this.utl.formatDate(element.uploadedOn, 'dd-MMM-yyyy HH:mm');
                        element.fileSize = element.fileSize.toFixed(2) + " KB";
                    });
                    this.attList = tmpattList;
                }
                else {
                    this.mesErr = o.message;
                    this.uploadModal.show();
                }

            }
        }, err => console.log(err));
        setTimeout(function () {
            document.getElementById('preloader').style.display = 'none';
        }, 500);
    }

    cancelUpload(id: string): void {
        this.uploadInput.emit({ type: 'cancel', id: id });
    }

    removeFile(id: string): void {
        this.uploadInput.emit({ type: 'remove', id: id });
    }

    removeAllFiles(): void {
        this.files = [];
        this.strFileName = "";
        this.uploadInput.emit({ type: 'removeAll' });
    }

    public getDetail(sfId) {
        document.getElementById('preloader').style.display = 'block';

        this.pro.getById(sfId).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.entity = rsp.result;

                if (this.entity.isCreditNote) {
                    this.checkCd = true;

                    if (this.entity.creditNotes != null) {
                        let tmpCNList = this.entity.creditNotes;
                        let i = 0;
                        var option = {
                            minimumFractionDigits: 2,
                            maximumFractionDigits: 2
                        };
                        var formatter = new Intl.NumberFormat("en-US", option);
                        tmpCNList.forEach(element => {
                            i++;
                            element.no = i;
                            element.status = "Accepted";
                            //element.applyCN = element.unappliedReason == null || element.appliedInvoiceNo == null ? "checked" : "";
                            element.applyCN = element.appliedInvoiceNo == null ? "" : "checked";
                            //let cnAmount = element.creditAmount == null ? '' : Intl.NumberFormat('en-US', { style: 'currency', currency: element.currencyIsoCode }).format(element.creditAmount);
                            let cnAmount = element.creditAmount == null ? '' : element.creditAmount = element.currencyIsoCode + " " + formatter.format(element.creditAmount);
                            let appInvNo = element.appliedInvoiceNo == null ? '' : element.appliedInvoiceNo;
                            element.amountInvNo = cnAmount + ' / ' + appInvNo;
                            if (cnAmount == '' || appInvNo == '') {
                                let re = / \/ /gi;
                                element.amountInvNo = element.amountInvNo.replace(re, '');
                            }
                            element.customerBranch = element.customer + ' / ' + element.customerBranch;
                        });
                        this.cnList = tmpCNList;
                    }
                }
                else {
                    this.checkIv = true;
                    if (this.entity.invoices != null) {
                        let tmpIVList = this.entity.invoices;
                        let i = 0;
                        let isRejected = false;
                        var option = {
                            minimumFractionDigits: 2,
                            maximumFractionDigits: 2
                        };
                        var formatter = new Intl.NumberFormat("en-US", option);
                        tmpIVList.forEach(element => {
                            i++;
                            element.no = i;
                            element.customerexcel = element.customerFromExcel;
                            element.supplierExcel = element.supplierExcel;
                            //element.invoiceAmount = Intl.NumberFormat('en-US', { style: 'currency', currency: element.currencyIsoCode }).format(element.invoiceAmount);
                            element.invoiceAmount = element.currencyIsoCode + " " + formatter.format(element.invoiceAmount);
                            element.customerBranch = element.customer + ' / ' + element.customerBranch;
                            element.creditDate = this.utl.formatDate(element.invoiceDate, 'dd-MMM-yyyy') + ' / ' + element.creditPeriod;
                            let po = element.po == null ? '' : element.po;
                            let contract = element.contract == null ? '' : element.contract;
                            element.po = po + ' / ' + contract;
                            if (po == '' || contract == '') {
                                let re = / \/ /gi;
                                element.po = element.po.replace(re, '');
                            }
                            if ((this.entity.portalStatus == 'Pending Authorisation' || this.entity.portalStatus == 'Authorised') && element.status == 'Unfunded') {
                                element.status = 'Processing';
                            }
                            if (this.entity.portalStatus == 'Submitted') {
                                element.status = 'Undunded';
                            }
                            if (element.status == 'Rejected') {
                                isRejected = true;
                            }
                        });

                        if (!isRejected) {
                            delete this.settingsIv.columns.rejectReason;
                        }

                        this.ivList = tmpIVList;
                        if (this.entity.documentType == "Invoice") {
                            delete this.settingsIv.columns.supplier;
                            delete this.settingsIv.columns.supplierFromExcel;
                        }
                        else {
                            delete this.settingsIv.columns.customerBranch;
                            delete this.settingsIv.columns.customerexcel;
                        }
                    }
                }
                if (this.entity.attachments != null) {
                    let tmpattList = this.entity.attachments
                    let i = 0;
                    tmpattList.forEach(element => {
                        i++;
                        element.no = i;
                        element.uploadedOn = this.utl.formatDate(element.uploadedOn, 'dd-MMM-yyyy HH:mm');
                        element.fileSize = element.fileSize.toFixed(2) + " KB";
                    });
                    this.attList = tmpattList;
                }
                this.checkAtt = true;
            }
        }, (err) => {
            console.log(err);
        });

        setTimeout(function () {
            document.getElementById('preloader').style.display = 'none';
        }, 500);
    }
    public UpdateSchedule() {
        document.getElementById('preloader').style.display = 'block';
        let x = {
            scheduleNo: this.entity.scheduleNo,
            id: this.entity.id
        }
        this.pro.update(x).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {

            }
        }, (err) => {
            console.log(err);
        });

        setTimeout(function () {
            document.getElementById('preloader').style.display = 'none';
        }, 500);
    }

    public Authorise() {
        let x = {

            scheduleNo: this.entity.scheduleNo,
            portalStatus: "Authorised",
            id: this.entity.id
        }
        this.pro.update(x).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.entity.portalStatus = "Authorised";
                this.title = 'Confirmation';
                this.msgInfo = 'Schedule is updated successfully!';
                this.infoModal.show();
            }
        }, (err) => {
            console.log(err);
        });
    }

    onDelete(event) {
        if (window.confirm('Are you sure, you want to delete?')) {
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
        } else {
            event.confirm.reject();
        }
    }

    public onDownload(e) {
        let x = {
            "path": e.data.path,
            "file": e.data.file,
            "aws": true
        };
        this.filepro.download(x)
            .subscribe(blob => {
                saveAs(blob, e.data.name);
            });
    }
}
