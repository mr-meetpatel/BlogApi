# BlogApi

This is blog api to manage blog data.

## Requirements 

1.postgres database

## How to run project.

1. Run Migration first this will create table in blogs_dev database.

```bash
./gradlew migration:run
```

2. Run Project

```bash
./gradlew runAPi
```

## API Signature

1. CREATE USER API

```bash
API: /api/v1/users/
Method Type: POST
body: {
 userName:String
}
```

2. CREATE USER BLOG
```bash
API: /api/v1/user/{userId}/blog
Method Type: POST
body: {
  title: string,
  content: string,
  published: boolean
}
```

3. UPDATE BLOG
```bash
API: /api/v1/user/{userId}/blog/{blogId}
Method Type: PUT
body: {
  title: string,
  content: string,
  published: true
}
```

4. DELETE BLOG
```bash
API: /api/v1/user/{userId}/blog/{blogId}
Method Type: DELETE
```

5. DISPLAY BLOG
```bash
API: /api/v1/blogs/
Method Type: GET
```

6. DISPLAY USER BLOG
```bash
API: /api/v1/user/{userId}/blogs
Method Type: GET
```

7. DISPLAY ALL USER
```bash
API: /api/v1/users
Method Type: GET
```

8. DISPLAY USER INFORMATION
```bash
API: /api/v1/user/{userId}
Method Type: GET
```
