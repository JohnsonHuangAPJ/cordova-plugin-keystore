var exec = require("cordova/exec");

exports.setItem = function (key, value, accessGroup = "") {
  return new Promise((resolve, reject) => {
    exec(resolve, reject, "KeyStore", "setItem", [key, value, accessGroup]);
  });
};

exports.getItem = function (key, accessGroup = "") {
  return new Promise((resolve, reject) => {
    exec(resolve, reject, "KeyStore", "getItem", [key, accessGroup]);
  });
};

exports.deleteItem = function (key, accessGroup = "") {
  return new Promise((resolve, reject) => {
    exec(resolve, reject, "KeyStore", "deleteItem", [key, accessGroup]);
  });
};

exports.clear = function (accessGroup = "") {
  return new Promise((resolve, reject) => {
    exec(resolve, reject, "KeyStore", "clear", [accessGroup]);
  });
};
