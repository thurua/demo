import { Component, OnInit } from '@angular/core';
import { HTTP } from 'app/utilities';
import { FileProvider, CommonProvider } from 'app/providers';
import { FileUploader } from 'ng2-file-upload';

const URL = 'http://localhost:8080/api/upload';

@Component({
    selector: 'app-file',
    templateUrl: './file.component.html',
    styleUrls: ['./file.component.scss']
})

export class FileComponent implements OnInit {
    public uploader: FileUploader = new FileUploader({ url: URL, itemAlias: 'photo' });
    public file: any;
    public listClient: any[] = [];
    date = Date.now();

    constructor(private pro: FileProvider,
        private proCommon: CommonProvider) { }

    ngOnInit() {
        this.getClient();
        this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; };

        this.uploader.onCompleteItem = (item: any, response: any, status: any, header: any) => {
            console.log('quy', item, status, response);
        };
    }

    public fileChanged(e) {
        this.file = e.target.files[0];
    }

    public uploadDocument() {
        let o = {
            clientName: 1,
            scheduleAcceptanceDate: "2",
            amendSchedule: true,
            amendScheduleNo: "no1"
        };
        let s = JSON.stringify(o);

        this.pro.upload(this.file, s).subscribe((rsp: any) => {
            if (rsp.body != undefined) {
                let o = JSON.parse(rsp.body);

                if (o.status === HTTP.STATUS_SUCCESS) {
                    alert("File is uploaded!");
                }
                else {
                    console.log(o.message);
                }
            }
        }, err => console.log(err));
    }

    private getClient() {
        // this.proCommon.searchAccount().subscribe((rsp: any) => {
        //     if (rsp.status === HTTP.STATUS_SUCCESS) {
        //         this.listClient = rsp.result.data;
        //     }
        //     else {
        //         console.log(rsp.message);
        //     }
        // }, err => console.log(err));
    }
}