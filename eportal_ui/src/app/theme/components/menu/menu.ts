import { Menu } from './menu.model';

export const verticalMenuItems = [
    new Menu(1, 'Dashboard', '/pages/dashboard', null, 'tachometer', null, false, 0),
     new Menu(10, 'Schedules', 'pages/schedule', null, 'history', null, false, 0),
     new Menu(20, 'Invoices', '/login', null, 'shield', null, false, 0),
     new Menu(30, 'Credit Notes', '/register', null, 'tag', null, false, 0),
     new Menu(40, 'Cash Disbursements', '/pages/blank', null, 'money', null, false, 0),
     new Menu(50, 'Client Accounts', '/pages/file', null, 'cogs', null, true, 0),
     new Menu(50, 'Summary', '/pagenotfound', null, 'briefcase', null, false, 50),
     new Menu(60, 'Details', null, null, 'newspaper-o', null, false, 50),
     new Menu(70, 'Help', null, null, 'chain', null, false, 0),

    // new Menu(142, 'Level 3', null, null, 'folder-open-o', null, true, 141),
    // new Menu(143, 'Level 4', null, null, 'folder-open-o', null, true, 142),
    // new Menu(144, 'Level 5', null, null, 'folder-o', null, false, 143),
    // new Menu(200, 'External Link', null, 'http://themeseason.com', 'external-link', '_file', false, 0)
]

export const horizontalMenuItems = [
    new Menu(1, 'Dashboard', '/pages/dashboard', null, 'tachometer', null, false, 0),
    // new Menu(40, 'Pages', null, null, 'file-text-o', null, true, 0),
    // new Menu(43, 'Login', '/login', null, 'sign-in', null, false, 0),
    // new Menu(44, 'Register', '/register', null, 'registered', null, false, 0),
    // new Menu(45, 'Blank', '/pages/blank', null, 'file-o', null, false, 40),
    // new Menu(45, 'file', '/pages/file', null, 'file-o', null, false, 40),
    // new Menu(46, 'Error', '/pagenotfound', null, 'exclamation-circle', null, false, 40),
    // new Menu(200, 'External Link', null, 'http://themeseason.com', 'external-link', '_file', false, 0)
]