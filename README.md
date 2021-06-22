# KeyStore

## About

cordova-plugin-keystore is a cordova plugin that uses iOS's keychain to store and retrieve key-value items.
The plugin is mainly a javascript implementation of the [KeychainSwift](https://github.com/evgenyneu/keychain-swift) library.

## Keychain sharing

iOS Keychain has the ability to share keychains between apps so long as they have the same keychain access groups
and same TeamID.

You can read more about keychain sharing from the [apple developer docs](https://developer.apple.com/documentation/security/keychain_services/keychain_items/sharing_access_to_keychain_items_among_a_collection_of_apps)

To use keychain sharing with cordova-plugin-keystore, you should provide an accessGroup string to the functions where shown.

## Usage

the plugin handle for KeyStore is `cordova.plugins.KeyStore` and all functions will be called from there.
All functions return a javascript `Promise`.

```javascript
const KeyStore = cordova.plugins.KeyStore;
```

### Setting a key/value pair

```javascript
const key = "hello";
const value = "world";
const accessGroup = "XXXXXX.accessGroup"; // OPTIONAL keychain access group

await KeyStore.setItem(key, value); // Without an access group
await KeyStore.setItem(key, value, accessGroup); // With an access group.
```

### Retrieving a value from a key

```javascript
const key = "hello";
const accessGroup = "XXXXXX.accessGroup"; // OPTIONAL keychain access group

const value = await KeyStore.getItem(key); // Without an access group
const valueAccessGroup = await KeyStore.getItem(key, accessGroup); // With an access group.

console.log(value); // "world"
console.log(valueAccessGroup); // "world"
```

### Deleting a key

```javascript
const key = "hello";
const accessGroup = "XXXXXX.accessGroup"; // OPTIONAL keychain access group

await KeyStore.deleteItem(key); // Without an access group
await KeyStore.deleteItem(key, accessGroup); // With an access group.
```

### Clearing all keys

```javascript
const key = "hello";
const accessGroup = "XXXXXX.accessGroup"; // OPTIONAL keychain access group

await KeyStore.clear(); // Without an access group
await KeyStore.clear(accessGroup); // With an access group.
```
