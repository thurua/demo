import { Injectable } from '@angular/core';
declare var JSEncrypt: any;

/**
 * @author ToanNguyen 2018-Oct-18 (verified)
 */
@Injectable()
export class Rsa {
    public privateKey: string;
    public publicKey: string;
    public enabled: boolean;
    private jse = new JSEncrypt();

    constructor() {
        this.enabled = false;
    }

    /**
     * Encrypts the text with the public key RSA
     * @param s Plain text to be encrypted
     */
    public encrypt(s: string): string {
        if (!this.enabled) {
            return s;
        }

        this.jse.setPublicKey(this.publicKey);
        return this.jse.encrypt(s);
    }

    /**
     * Decrypts the text with the private key RSA
     * @param s Cypher text to be decrypted
     */
    public decrypt(s: string): string {
        if (!this.enabled) {
            return s;
        }

        this.jse.setPrivateKey(this.privateKey);
        return this.jse.decrypt(s);
    }
}