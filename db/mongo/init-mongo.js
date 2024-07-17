db.createUser({
        user: 'root',
        pwd: 'toor',
        roles: [
            {
                role: 'readWrite',
                db: 'testDB',
            },
        ],
    });
db.createCollection('app_users', { capped: false });

db.app_users.insert([
    { 
        "username": "ragnar777", 
        "dni": "VIKI771012HMCRG093", 
        "enabled": true, 
        "password_not_encrypted": "s3cr3t",
        "password": "$2a$10$WoSZbN4y4hig.nX/xwlXKO1OZz2VNdIhdY/gpPdPcDkm5h2SmI61i", 
        "role": 
        {
            "granted_authorities": ["ROLE_USER"]
        } 
    },
    { 
        "username": "heisenberg", 
        "dni": "BBMB771012HMCRR022", 
        "enabled": true, 
        "password_not_encrypted": "p4sw0rd",
        "password": "$2a$10$ePmgNyZ8vN1qK0ZK4Qw2pu3NgxoHMT2MqS6cNEVcFg0G/fbM/uRmG", 
        "role": 
        {
            "granted_authorities": ["ROLE_USER"]
        } 
    },
    { 
        "username": "misterX", 
        "dni": "GOTW771012HMRGR087", 
        "enabled": true, 
        "password_not_encrypted": "misterX123",
        "password": "$2a$10$OMKcCDPnFCEYyQIFbcjM5.YuBOd4SXa5MQYCmeBpcg./oNoGHSXT2", 
        "role": 
        {
            "granted_authorities": ["ROLE_USER", "ROLE_ADMIN"]
        } 
    },
    { 
        "username": "neverMore", 
        "dni": "WALA771012HCRGR054", 
        "enabled": true, 
        "password_not_encrypted": "4dmIn",
        "password": "$2a$10$9feiA0/9KpI2ugLs.ztLPeRcxaAmzDL9Oi1clhtK6lbdyCyeGQq8O", 
        "role": 
        {
            "granted_authorities": ["ROLE_ADMIN"]
        } 
    }
]);