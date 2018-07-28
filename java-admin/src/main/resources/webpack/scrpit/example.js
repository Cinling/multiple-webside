var shell = require('../node_modules/shelljs/shell.js');

shell.ls("*").forEach(function (file) {
    console.log(file);
});