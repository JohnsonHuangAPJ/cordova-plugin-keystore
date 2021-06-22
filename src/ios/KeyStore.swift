import KeychainSwift

@objc(KeyStore) class KeyStore : CDVPlugin {

    @objc(getItem:)
  func getItem(command: CDVInvokedUrlCommand) {
    var pluginResult = CDVPluginResult(
      status: CDVCommandStatus_ERROR
    )
      let keychain = KeychainSwift();

    let key = command.arguments[0] as? String ?? ""
    let accessGroup = command.arguments[1] as? String ?? ""

    if key.characters.count <= 0 {
        pluginResult = CDVPluginResult(
            status: CDVCommandStatus_ERROR,
            messageAs: "Key must not be blank"
        )
        self.commandDelegate!.send(
            pluginResult,
            callbackId: command.callbackId
        )
        return
    }

    // access group defaults to nothing unless otherwise written
    if accessGroup.characters.count > 0 {
        keychain.accessGroup = accessGroup
    }
        
    let value = keychain.get(key);
    
    pluginResult = CDVPluginResult(
        status: CDVCommandStatus_OK,
        messageAs: value
    )

    self.commandDelegate!.send(
      pluginResult,
      callbackId: command.callbackId
    )
  }

  @objc(setItem:)
  func setItem(command: CDVInvokedUrlCommand) {
    var pluginResult = CDVPluginResult(
      status: CDVCommandStatus_ERROR
    )
      let keychain = KeychainSwift();


    let key = command.arguments[0] as? String ?? ""
    let value = command.arguments[1] as? String ?? ""
    let accessGroup = command.arguments[2] as? String ?? ""

    if key.characters.count <= 0 {
        pluginResult = CDVPluginResult(
            status: CDVCommandStatus_ERROR,
            messageAs: "Key must not be blank"
        )
        self.commandDelegate!.send(
            pluginResult,
            callbackId: command.callbackId
        )
        return
    }

    // access group defaults to nothing unless otherwise written
    if accessGroup.characters.count > 0 {
        keychain.accessGroup = accessGroup
    }
    

    keychain.set(value, forKey: key);
    
    pluginResult = CDVPluginResult(
        status: CDVCommandStatus_OK,
        messageAs: "Success"
    )

    self.commandDelegate!.send(
      pluginResult,
      callbackId: command.callbackId
    )
  }

  @objc(deleteItem:)
  func deleteItem(command: CDVInvokedUrlCommand) {
    var pluginResult = CDVPluginResult(
      status: CDVCommandStatus_ERROR
    )
      let keychain = KeychainSwift();


    let key = command.arguments[0] as? String ?? ""
    let accessGroup = command.arguments[1] as? String ?? ""

    if key.characters.count <= 0 {
        pluginResult = CDVPluginResult(
            status: CDVCommandStatus_ERROR,
            messageAs: "Key must not be blank"
        )
        self.commandDelegate!.send(
            pluginResult,
            callbackId: command.callbackId
        )
        return
    }

    // access group defaults to nothing unless otherwise written
    if accessGroup.characters.count > 0 {
        keychain.accessGroup = accessGroup
    }
    

    keychain.delete(key);
    
    pluginResult = CDVPluginResult(
        status: CDVCommandStatus_OK,
        messageAs: "Success"
    )

    self.commandDelegate!.send(
      pluginResult,
      callbackId: command.callbackId
    )
  }


  @objc(clear:)
  func clear(command: CDVInvokedUrlCommand) {
    let keychain = KeychainSwift();
    let accessGroup = command.arguments[0] as? String ?? ""

    // access group defaults to nothing unless otherwise written
    if accessGroup.characters.count > 0 {
        keychain.accessGroup = accessGroup
    }

    keychain.clear();
    
    var pluginResult = CDVPluginResult(
        status: CDVCommandStatus_OK,
        messageAs: "Success"
    )

    self.commandDelegate!.send(
      pluginResult,
      callbackId: command.callbackId
    )
  }
}