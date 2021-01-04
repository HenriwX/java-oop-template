package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        for (Author a : authors) {
            if (a.getName() == author.getName() && a.getLastName() == author.getLastName()) {
                return false;
            }
        }

        Author[] newAuthors = authors;
        authors = new Author[newAuthors.length + 1];

        for (int i = 0; i < newAuthors.length; i++) {
            authors[i] = newAuthors[i];
        }

        authors[newAuthors.length] = author;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (author.getName() == name && author.getLastName() == lastname) {
                return author;
            }
        }

        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (this.findByFullName(author.getName(), author.getLastName()) != null) {
            Author[] newAuthors = authors;
            for (int i = 0; i < authors.length; i++) {
                newAuthors[i] = authors[i];
            }

            authors = new Author[newAuthors.length - 1];
            int count = 0;
            for (Author a : authors) {
                if (!a.getName().equals(author)) {
                    authors[count] = a;
                    count++;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int count() {
        return authors.length;
    }
}
