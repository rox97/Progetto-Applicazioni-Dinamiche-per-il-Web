module.exports = {
    client: {
        service : {
            name: 'frontend',
            url: 'http://localhost:8080/graphql',
        },
        includes: [
            '**/*.graphql',
            '**/*.vue',
            '**/*.js',
            ],
    }
}