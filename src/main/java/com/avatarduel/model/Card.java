package com.avatarduel.model;

abstract class Card{
    protected int id;
    protected String name;
    protected Element element;
    protected String description;
    protected String imagePath;

    public Card(int id, String name, Element element, String description, String imagePath){
        this.id = id;
        this.name = name;
        this.element = element;
        this.description = description;
        this.imagePath = imagePath;
    }

    public String getName()
    {
        return this.name;
    }

    public Element getElement()
    {
        return this.element;
    }

    public String getDescription()
    {
        return this.description;
    }

    public String imagePath()
    {
        return this.imagePath;
    }
}