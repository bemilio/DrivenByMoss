// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017-2019
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.bitwig.framework.daw.data;

import de.mossgrabers.framework.controller.IValueChanger;
import de.mossgrabers.framework.daw.IHost;
import de.mossgrabers.framework.daw.data.ILayer;
import de.mossgrabers.framework.daw.resource.ChannelType;

import com.bitwig.extension.controller.api.Channel;
import com.bitwig.extension.controller.api.DeviceBank;


/**
 * The data of a layer.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class LayerImpl extends ChannelImpl implements ILayer
{
    private final DeviceBank deviceBank;


    /**
     * Constructor.
     *
     * @param host The DAW host
     * @param valueChanger The valueChanger
     * @param layer The layer
     * @param index The index of the channel in the page
     * @param numSends The number of sends of a bank
     * @param numDevices The number of devices of a bank
     */
    public LayerImpl (final IHost host, final IValueChanger valueChanger, final Channel layer, final int index, final int numSends, final int numDevices)
    {
        super (host, valueChanger, layer, index, numSends);

        this.deviceBank = layer.createDeviceBank (numDevices);
        layer.addIsSelectedInEditorObserver (this::setSelected);
    }


    /** {@inheritDoc} */
    @Override
    public ChannelType getType ()
    {
        return ChannelType.LAYER;
    }


    /** {@inheritDoc} */
    @Override
    public void enter ()
    {
        this.deviceBank.getItemAt (0).selectInEditor ();
    }
}