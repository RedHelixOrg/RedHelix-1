package org.redhelix.redhx.server.db.test2;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyModel
{

    private int result;

    public MyModel(int result)
    {
        super();
        this.result = result;
    }

    private MyModel()
    {
        super();
    }

    public int getResult()
    {
        return result;
    }

    public void setResult(int result)
    {
        this.result = result;
    }
}
