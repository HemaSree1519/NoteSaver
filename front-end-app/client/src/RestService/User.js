export const getUserByEmail = async (email) => {
    try {
        const response = await fetch('http://localhost:1235/notesaver/users/' + email);
        if (response.status === 200) {
            return await response.json();
        }
        else {
            return false;
        }
    } catch (e) {
    }
};

export const createUser = (user) => {
    return new Promise(async function (resolve, reject) {
        try {
            const response = await fetch('http://localhost:1235/notesaver/users/add', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user),
            });
            resolve(response.status);
        } catch (e) {
            reject(e)
        }
    });
};