import {getUserByEmail} from "../../RestService/User";
import {CODES} from "../../ErrorCodes/codes";

export const isAuthenticated = (email, password) => {
    return new Promise(async function (resolve, reject) {
        try {
            if (email !== '' && password !== '') {
                await getUserByEmail(email).then((user) => {
                    if (user) {
                        if (user['password'] === password) {
                            resolve(true);
                        }
                        else resolve(CODES[106]);
                    }
                    else resolve(CODES[107]);
                })
            }
            else resolve(CODES[108])
        } catch (e) {
            reject(e)
        }
    });
};