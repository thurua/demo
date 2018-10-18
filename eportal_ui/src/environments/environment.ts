// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
    production: false,
    showLog: true,

    apiUrl: 'http://localhost:8080/',
    imgUrl: 'http://localhost:8080/assets/img/',

    //apiUrl: 'https://ifscapital-dev.herokuapp.com/',
    //imgUrl: 'https://ifscapital-dev.herokuapp.com/assets/img/'
};