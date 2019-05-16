export const createUser = jest.fn()
    .mockImplementationOnce(() => {
        return new Promise(async function (resolve, reject) {
            try {
                console.log("Check email :");
                resolve(400);
            } catch (e) {
                reject(e)
            }
        });
    });
