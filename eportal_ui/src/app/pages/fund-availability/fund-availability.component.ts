import { Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-fund-availability',
  templateUrl: './fund-availability.component.html',
  styleUrls: ['./fund-availability.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class FundAvailabilityComponent implements OnInit {

    public settings = {
        selectMode: 'single',  //single|multi
        hideHeader: false,
        hideSubHeader: false,
        actions: {
            add: false,
            edit: false,
            delete: false,
            custom: [{ name: 'RequestForFund', title: '<button type="button">Request For Fund</button>' }],
            position: 'right'
        },
        handle: {
            editable: false
        },
        noDataMessage: 'No data found',
        columns: {
            AccountRefernceNo: {
                title: 'Account Reference No.',
                type: 'string',
                filter: false,
            },
            ProductTypeName: {
                title: 'Product Type Name',
                type: 'string',
                filter: false
            },
            InvestmentLimit: {
                title: 'Investment Limit',
                type: 'string',
                filter: false
            },
            NetDepts: {
                title: 'Net O/S Depts',
                type: 'string',
                filter: false
            },
            CurrentFundInUse: {
                title: 'Current Fund in Use',
                type: 'string',
                filter: false
            },
            FundAvailability: {
                title: 'Fund Availability',
                type: 'string',
                filter: false
            },
            FundAvailabilityDated: {
                title: 'Fund Availability Dated',
                type: 'string',
                filter: false
            }
        },
        
    };
  constructor() { }

  ngOnInit() {
  }

}
