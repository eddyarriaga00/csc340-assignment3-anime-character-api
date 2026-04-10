<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle!"All Characters"}</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<header>
    <h1>Anime Character Directory</h1>
    <nav>
        <a href="/characters">All Characters</a>
        <a href="/characters/createForm">Create Character</a>
        <a href="/about">About</a>
    </nav>
</header>

<main>
    <h2>${pageTitle!"All Characters"}</h2>

    <section class="search-row">
        <form action="/characters/search" method="get">
            <label for="name">Search by Name</label>
            <input id="name" type="text" name="name" value="${nameQuery!''}" placeholder="Enter part of a name">
            <button type="submit">Search</button>
        </form>

        <form action="/characters/category" method="get">
            <label for="category">Find by Category</label>
            <input id="category" type="text" name="category" value="${categoryQuery!''}" placeholder="Hero, Anti-Hero, Slayer...">
            <button type="submit">Find</button>
        </form>
    </section>

    <#if formError??>
        <p class="form-error">${formError}</p>
    </#if>

    <#if characterList?has_content>
        <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>Series</th>
                <th>Category</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <#list characterList as character>
                <tr>
                    <td>${character.name}</td>
                    <td>${character.series}</td>
                    <td>
                        <a href="/characters/category/${character.category?url}">${character.category}</a>
                    </td>
                    <td>
                        <a href="/characters/${character.characterId?c}">Details</a>
                        <a href="/characters/updateForm/${character.characterId?c}">Update</a>
                        <a href="/characters/delete/${character.characterId?c}">Delete</a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        <p>No characters found.</p>
    </#if>
</main>
</body>
</html>
