<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${character.name} Details</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<header>
    <h1>Character Details</h1>
    <nav>
        <a href="/characters">All Characters</a>
        <a href="/characters/createForm">Create Character</a>
        <a href="/about">About</a>
    </nav>
</header>

<main>
    <h2>${character.name}</h2>
    <p><strong>ID:</strong> ${character.characterId?c}</p>
    <p><strong>Series:</strong> ${character.series}</p>
    <p><strong>Category:</strong> ${character.category}</p>
    <p><strong>Signature Ability:</strong> ${character.signatureAbility}</p>
    <p><strong>Description:</strong> ${character.description}</p>

    <div class="actions">
        <a href="/characters/updateForm/${character.characterId?c}">Update</a>
        <a href="/characters/delete/${character.characterId?c}">Delete</a>
        <a href="/characters">Back to All Characters</a>
    </div>
</main>
</body>
</html>
