package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] newSchoolBooks = schoolBooks;
        schoolBooks = new SchoolBook[newSchoolBooks.length + 1];

        for (int i = 0; i < newSchoolBooks.length; i++) {
            schoolBooks[i] = newSchoolBooks[i];
        }

        schoolBooks[newSchoolBooks.length] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                return schoolBooks;
            }
        }

        return new SchoolBook[0];
    }

    @Override
    public boolean removeByName(String name) {
        int books_check = findByName(name).length;
        SchoolBook[] newSchoolBooks = schoolBooks;
        for (int i = 0; i < schoolBooks.length; i++) {
            newSchoolBooks[i] = schoolBooks[i];
        }

        schoolBooks = new SchoolBook[newSchoolBooks.length - books_check];
        int count = 0;
        for (SchoolBook schoolBook : schoolBooks) {
            if (!schoolBook.getName().equals(name)) {
                schoolBooks[count] = schoolBook;
                count++;
            }
        }
        if (books_check > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
