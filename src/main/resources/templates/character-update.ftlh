<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Character</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<header>
    <h1>Update Character</h1>
    <nav>
        <a href="/characters">All Characters</a>
        <a href="/about">About</a>
    </nav>
</header>

<main>
    <form action="/characters/update" method="post" class="form-card">
        <input type="hidden" name="characterId" value="${character.characterId?c}">

        <label for="name">Name</label>
        <input id="name" type="text" name="name" value="${character.name}" required>

        <label for="series">Series</label>
        <input id="series" type="text" name="series" value="${character.series}" required>

        <label for="category">Category</label>
        <input id="category" type="text" name="category" value="${character.category}" required>

        <label for="signatureAbility">Signature Ability</label>
        <input id="signatureAbility" type="text" name="signatureAbility" value="${character.signatureAbility}" required>

        <label for="description">Description</label>
        <textarea id="description" name="description" rows="5" required>${character.description}</textarea>

        <button type="submit">Save Changes</button>
        <a href="/characters/${character.characterId?c}">Cancel</a>
    </form>
</main>
</body>
</html>
