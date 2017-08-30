package za.co.adhd_developers.resources;

/**
 * Created by Grant on 2017/07/31.
 */
public class TreeBranch
{
    public long myNumber = 0;
    public TreeBranch left = null;
    public TreeBranch right = null;

    public long getLargestChain()
    {
        if (left != null && right != null)
        {
            return Math.max(this.myNumber + left.getLargestChain(), this.myNumber + right.getLargestChain());
        }
        return this.myNumber;
    }
}
